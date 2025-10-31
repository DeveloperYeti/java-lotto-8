package method;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.util.ArrayList;
import java.util.List;

public class Method {
    public enum LottoNumber {


        startInclusive(1), // 1로 변경 (로또 번호는 1~45)
        endInclusive(45),
        count(6);

        private final int value;

        LottoNumber(int value) {
            this.value = value;
        }


        public int getValue() {
            return value;
        }
    }

    enum LottoResult {
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

        // 보너스 번호 입력 받는 로직 따로 관리 int 형으로
        private static int inputBonusNumber() {
            System.out.println("보너스 번호를 입력해 주세요.");
            String bonus = Console.readLine();
            return Integer.parseInt(bonus.trim());
        }

        // 돈 입력.
        private static int getMoeny() {
            String getMoney = Console.readLine();
            return Integer.parseInt(getMoney.trim());
        }

        public int getMoneyWithValidation() {
            while (true) {
                try {
                    int money = getMoeny(); // 기존 메서드명 유지
                    if (money <= 0 || money % 1000 != 0)
                        throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위로 입력해야 합니다.");
                    return money;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // 돈에 맞게 반복 횟수 만큼 로또 구현하는 로직 구현.
        public void tryChance() {
            int chance = getMoneyWithValidation();
            List<List<Integer>> myLottos = saveLotto(chance);
            System.out.println("[translate:구매한 로또 번호 목록:]");
            for (List<Integer> lotto : myLottos) {
                System.out.println(lotto);
            }
            //로또 항목별 금액 값 및 리스트 값 출력 로직.
        }

        // 로또 번호 뽑는 로직
        public List<Integer> pickLotto() {
            return Randoms.pickUniqueNumbersInRange(
                    LottoNumber.startInclusive.getValue(),
                    LottoNumber.endInclusive.getValue(),
                    LottoNumber.count.getValue()
            );
        }

        // 리스트 안에 로또 리스트들을 저장.
        public List<List<Integer>> saveLotto(int count) {
            List<List<Integer>> lottoList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                lottoList.add(pickLotto());
            }
            return lottoList;
        }

        //뽑을 때마다 적립금 올라가는 로직 각 항목별로 올리기
        public int savingMoneyUp(int current, int amount) {
            return current + amount;
        }


        // 로또 검사 로직
        private static List<Integer> inputAndValidateLotto() {
            int bonus = inputBonusNumber();
            List<Integer> winNumbers = inputWinningNumber();
            winNumbers.add(bonus);
            return winNumbers;
        }


        // 로또 번호 입력 받는 로직
        public static List<Integer> inputWinningNumber() {
            System.out.println("당첨 번호를 입력해 주세요.");
            String winning = Console.readLine();
            List<Integer> result = parseAndValidateNumber(winning);
            checkoutRepeat(result);
            return result;
        }

        public static void checkoutRepeat(List<Integer> result) {
            for (int i = 0; i < result.size(); i++) {
                checkout(result, i);
            }
        }

        public static void checkout(List<Integer> result, int i) {
            int value = result.get(i);
            if (LottoNumber.startInclusive.getValue() <= value
                    && value <= LottoNumber.endInclusive.getValue()) {
                return;
            }
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }


        // lotto 클래스를 사용하여 검증 및 getNumbers를 사용해서 List<Integer> 반환
        private static List<Integer> parseAndValidateNumber(String input) {
            String[] number = input.split(",");
            Lotto lotto = new Lotto(convertStrList(number));
            return lotto.getNumbers();
        }

        // <Integer> 형으로 입력을 받을 경우 각 부분에 대해서 문자열을 숫자로 바꿔주는 로직 구현.
        public static List<Integer> convertStrList(String[] arr) {
            List<Integer> result = new ArrayList<>();
            for (String s : arr) {
                result.add(Integer.parseInt(s.trim()));
            }
            return result;
        }
    }

    //로또 오름차순
    public void printSortedLottos(List<List<Integer>> lottos) {
        for (List<Integer> lotto : lottos) {
            lotto.sort(Integer::compareTo);
            System.out.println(lotto);
        }
    }

    //로또 항목 별 금액 값 및 리스트 출력
    public void printSaveMoney(List<Integer> moneyList) {
        System.out.println("[translate:각 로또의 누적 당첨금]");
        for (Integer money : moneyList) {
            System.out.println(money);
        }
    }
}


