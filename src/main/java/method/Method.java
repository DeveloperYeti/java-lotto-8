package method;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.util.*;

public class Method {
    public static final String ERROR_MESSAGE = "[ERROR]";
    public enum LottoNumber {
        startInclusive(1), endInclusive(45), count(6);
        private final int value;
        LottoNumber(int value) { this.value = value; }
        public int getValue() { return value; }
    }

    public enum LottoResult {
        FIRST(6, false, "6개 일치 (2,000,000,000원)", 2000000000),
        SECOND(5, true, "5개 일치, 보너스 볼 일치 (30,000,000원)", 30000000),
        THIRD(5, false, "5개 일치 (1,500,000원)", 1500000),
        FOURTH(4, false, "4개 일치 (50,000원)", 50000),
        FIFTH(3, false, "3개 일치 (5,000원)", 5000),
        MISS(0, false, "", 0);
        private final int matchCount;
        private final boolean bonus;
        private final String message;
        private final int prize;
        LottoResult(int matchCount, boolean bonus, String message, int prize) {
            this.matchCount = matchCount;
            this.bonus = bonus;
            this.message = message;
            this.prize = prize;
        }
        public String getMessage() { return message; }
        public int getPrize() { return prize; }
        public static LottoResult of(int matched, boolean bonusMatched) {
            if (matched == 6) return FIRST;
            if (matched == 5 && bonusMatched) return SECOND;
            if (matched == 5) return THIRD;
            if (matched == 4) return FOURTH;
            if (matched == 3) return FIFTH;
            return MISS;
        }
        public static LottoResult[] order() {
            return new LottoResult[] {FIFTH, FOURTH, THIRD, SECOND, FIRST};
        }
    }

    // 금액 입력만(단일 책임)
    public int getMoneyWithValidation() {
        String input = Console.readLine();
        if (!isNumeric(input)) {
            throw new IllegalArgumentException("[ERROR]");
        }
        int money = Integer.parseInt(input.trim());
        if (money <= 0 || money % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR]");
        }
        return money;
    }

    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    // 발매수량 계산
    public int divideMoney(int totalMoney) {
        return totalMoney / 1000;
    }

    // 로또 번호 1장 뽑기(중복 없음)
    public List<Integer> pickLotto() {
        return Randoms.pickUniqueNumbersInRange(
                LottoNumber.startInclusive.getValue(),
                LottoNumber.endInclusive.getValue(),
                LottoNumber.count.getValue()
        );
    }

    // n장 저장 및 오름차순 정렬 -> 가변 리스트 변경
    public List<List<Integer>> saveLotto(int count) {
        List<List<Integer>> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> lotto = new ArrayList<>(pickLotto());
            lotto.sort(Integer::compareTo);
            lottoList.add(lotto);
        }
        return lottoList;
    }

    // 모든 로또 출력(오름차순)
    public void printSortedLottos(List<List<Integer>> lottos) {
        for (List<Integer> lotto : lottos) {
            System.out.println(lotto);
        }
    }

    // 당첨번호 입력만
    public static List<Integer> inputWinningNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String winning = Console.readLine();
        return parseAndValidateNumber(winning);
    }

    // 당첨번호 검증(6개+중복+범위)
    public void validateWinningNumbers(List<Integer> winNumbers) {
        if (winNumbers.size() != LottoNumber.count.getValue())
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        if (new HashSet<>(winNumbers).size() != winNumbers.size())
            throw new IllegalArgumentException("[ERROR] 중복된 번호가 있습니다.");
        for (int n : winNumbers) {
            if (n < LottoNumber.startInclusive.getValue() || n > LottoNumber.endInclusive.getValue())
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    // 최종 검증 포함 입력
    public List<Integer> inputWinningNumberWithValidation() {
        while(true) {
            try {
                List<Integer> winNumbers = inputWinningNumber();
                validateWinningNumbers(winNumbers);
                return winNumbers;
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // , 처리
    private static List<Integer> parseAndValidateNumber(String input) {
        String[] number = input.split(",");
        List<Integer> result = new ArrayList<>();
        for (String s : number) {
            result.add(Integer.parseInt(s.trim()));
        }
        return result;
    }

    // 보너스 번호 입력
    private static int inputBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String bonus = Console.readLine();
        return Integer.parseInt(bonus.trim());
    }

    // 보너스 번호 검증
    public int inputBonusNumberValidation(List<Integer> winNumbers) {
        while (true) {
            try {
                int bonus = inputBonusNumber();
                if (winNumbers.contains(bonus))
                    throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
                if (bonus < LottoNumber.startInclusive.getValue() || bonus > LottoNumber.endInclusive.getValue())
                    throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
                return bonus;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 당첨 개수
    public int countMatch(List<Integer> myLotto, List<Integer> winNumbers) {
        int cnt = 0;
        for (int num : myLotto) {
            if (winNumbers.contains(num)) cnt++;
        }
        return cnt;
    }
    public boolean hasBonusMatch(List<Integer> myLotto, int bonus) {
        return myLotto.contains(bonus);
    }
    public LottoResult judge(List<Integer> myLotto, List<Integer> winNumbers, int bonus) {
        int matched = countMatch(myLotto, winNumbers);
        boolean bonusMatched = hasBonusMatch(myLotto, bonus);
        return LottoResult.of(matched, bonusMatched);
    }

    // 당첨 결과 집계
    public Map<LottoResult, Integer> getRankStatistics(List<List<Integer>> myLottos, List<Integer> win, int bonus) {
        Map<LottoResult, Integer> stats = new EnumMap<>(LottoResult.class);
        for (LottoResult result : LottoResult.values()) stats.put(result, 0);
        for (List<Integer> myLotto : myLottos) {
            LottoResult res = judge(myLotto, win, bonus);
            stats.put(res, stats.get(res) + 1);
        }
        return stats;
    }

    // 통계 출력
    public void printStatistics(Map<LottoResult, Integer> stats) {
        System.out.println("당첨 통계");
        System.out.println("---");
        for (LottoResult res : LottoResult.order()) {
            if (!res.getMessage().isEmpty()) {
                System.out.println(res.getMessage() + " - " + stats.get(res) + "개");
            }
        }
    }

    // 수익률 계산
    public double calculateProfit(Map<LottoResult, Integer> stats, int purchaseAmount) {
        long sum = 0;
        for (LottoResult result : LottoResult.values())
            sum += (long)result.getPrize() * stats.getOrDefault(result, 0);
        return ((double)sum / purchaseAmount) * 100;
    }
}
