package lotto;
import method.Method;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        try {
            // 기존 프로그램 흐름
            Method method = new Method();
            int purchaseMoney = method.getMoneyWithValidation();
            int purchaseCount = method.divideMoney(purchaseMoney);
            System.out.println(purchaseCount + "개를 구매했습니다.");
            List<List<Integer>> purchasedLottos = method.saveLotto(purchaseCount);
            method.printSortedLottos(purchasedLottos);
            List<Integer> winNumbers = method.inputWinningNumberWithValidation();
            int bonusNumber = method.inputBonusNumberValidation(winNumbers);
            Map<Method.LottoResult, Integer> stats = method.getRankStatistics(purchasedLottos, winNumbers, bonusNumber);
            method.printStatistics(stats);
            double profit = method.calculateProfit(stats, purchaseMoney);
            System.out.printf("총 수익률은 %.1f%%입니다.\n", profit);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            // 필요시: 프로그램 종료나 추가 로직, throw e;
        }
    }
}

