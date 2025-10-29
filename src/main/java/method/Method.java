package method;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Method {

    public static int getMoeny(){
        String getMoney = Console.readLine();
        int money = 0;
        money = Integer.parseInt(getMoney);
        return money;
    }
}
