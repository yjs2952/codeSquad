import java.util.Scanner;

public class BaseBallGame {

	public static void main(String[] arg) {

		String num = ""; 	// 맞춰야할 숫자
		String input = null; 	// 입력할 숫자
		int[] result = new int[2];
		
		Scanner sc = new Scanner(System.in);
		
		num = setNum(num);

		while (result[0] != 3) {
			//System.out.print("숫자를 입력해 주세요 ex)123 : ");
			System.out.print("숫자를 입력해 주세요 ex)" + num + " : ");
			input = sc.nextLine();

			if (input.length() != 3) {
				System.out.println("3자리 수만 입력 가능합니다.");
				continue;
			}

			result = compareNum(num, input);

			if (result[0] != 0 && result[1] != 0) {
				System.out.println(result[0] + " 스트라이크 " + result[1] + "볼");
			} else if (result[0] != 0 && result[1] == 0) {
				System.out.println(result[0] + " 스트라이크 ");
			} else if (result[0] == 0 && result[1] != 0) {
				System.out.println(result[1] + "볼");
			} else {
				System.out.println("낫싱");
			}
		}

		System.out.println("3개의 숫자를 모두 맞히셨습니다. 게임 종료");
		sc.close();
	}

	// 랜덤 숫자 3자리수 생성

	public static String setNum(String num) {

		int tempNum = 0;
		int randomNum = 0;

		while (num.length() < 3) {
			randomNum = (int) (Math.random() * 9) + 1; // 1 ~ 9 사이의 랜덤 숫자

			if (randomNum == tempNum) continue;

			num += String.valueOf(randomNum);
			tempNum = randomNum;
		}

		return num;
	}

	public static int[] compareNum(String num1, String num2) {

		int strikeCnt = 0;
		int ballCnt = 0;
		int[] result = new int[2];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (num2.charAt(i) == num1.charAt(j)) {
					if (i == j) {
						strikeCnt++;
					} else {
						ballCnt++; 
					}
				}
			}
		}

		result[0] = strikeCnt;
		result[1] = ballCnt;

		return result;
	}

}
