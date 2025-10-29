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

    // 돈에 맞게 반복 횟수 만큼 로또 구현하는 로직 구현.
    public void tryChance(){
        int chance = devideMoney();
        for(int i=0; i<chance;i++)
        {
            // 로또 번호 뽑는 로직 + 뽑을 때마다 적립금 올라가는 로직 각 항목별로 올리기
        }
        //로또 항목별 금액 값 및 리스트 값 출력 로직.
    }


}
