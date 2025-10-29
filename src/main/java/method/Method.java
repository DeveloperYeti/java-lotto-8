package method;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

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


}
