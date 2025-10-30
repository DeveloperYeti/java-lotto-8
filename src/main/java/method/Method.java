package method;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.util.ArrayList;
import java.util.List;

public class Method {
    // 돈 입력.
    public static int getMoeny(){
        String getMoney = Console.readLine();
        int money = 0;
        money = Integer.parseInt(getMoney);
        return money;
    }

    // 입력 받은 돈 1000원당 1장으로 변환
    public static int devideMoney(){
        int money = getMoeny();
        int sum = money/1000 ;
        return sum;
    }

    // 돈에 맞게 반복 횟수 만큼 로또 구현하는 로직 구현.
    public void tryChance(){
        int chance = devideMoney();
        for(int i=0; i<chance;i++)
        {
            // 로또 번호 뽑는 로직 + 뽑을 때마다 적립금 올라가는 로직 각 항목별로 올리기
        }
        //로또 항목별 금액 값 및 리스트 값 출력 로직.
    }
    // 로또 번호 뽑는 로직

    public List<Integer> pickLotto(){
        List<Integer> numbers = new ArrayList<>();


        numbers.add(1);

        return ;
    }

    //뽑을 때마다 적립금 올라가는 로직 각 항목별로 올리기
    public int savingMoneyUp(){

        return 0;
    }
    //로또 항목 별 금액 값 및 리스트 출력
    public List<Integer> printSaveMoney(List<Integer>money){

        return ;
    }
    // 로또 검사 로직
    private static List<Integer> inputAndValidateLotto() {

        // 여기서는 따로 저장하고 bonus 는 값을 입력받고 for를 사용해서 각 항목 검사해서 bonus 번호와 맞는게 있나 추가.
        int bonus = inputBonusNumber();
        return //저장된 값에 대해서 항목별로 검사하여 int 형 리스트로 변환 하는 로직();
    }
    // 로또 번호 입력 받는 로직
    private static int inpputWinningNumber(){
        System.out.println(System.out.println("당첨 번호를 입력해 주세요.");
        String winning = Console.readLine();

        return 
    }
    private static List<String> parseAndValidateNames(String input) {
        String[] names = input.split(",");
        Lotto lotto = new Lotto(convertStrList(names));

    }
    // <Integer> 형으로 입력을 받을 경우 각 부분에 대해서 문자열을 숫자로 바꿔주는 로직 구현.
    public static List<Integer> convertStrList(String[] arr) {
        List<Integer> result = new ArrayList<>();
        for (String s: arr) {
            result.add(Integer.parseInt(s));
        }
        return result;
    }

    // 보너스 번호 입력 받는 로직 따로 관리 int 형으로
    private static int inputBonusNumber(){
        System.out.println("보너스 번호를 입력해 주세요.");
        String bonus = Console.readLine();
        return Integer.parseInt(bonus);
    }


}
