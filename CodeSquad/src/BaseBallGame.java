import java.util.Scanner;

public class BaseBallGame {

	public static final int NUMBER_LENGTH = 3;

	public static void main(String[] arg) {

		String num = ""; // 맞춰야할 숫자
		String input = null; // 입력할 숫자
		int[] result = new int[2];

		Scanner sc = new Scanner(System.in);

		num = setNum(num);

		while (true) {

			if (result[0] == NUMBER_LENGTH) {

				System.out.println("3개의 숫자를 모두 맞히셨습니다. 게임을 다시 시작합니다.");
				num = setNum("");
			}

			//System.out.print("숫자를 입력해 주세요 ex)123 : ");
			System.out.print("숫자를 입력해 주세요 ex)" + num + " : ");
			
			input = sc.nextLine();

			if (check(input) < 0) {

				continue;
			}
			
			result = result(compareNum(num, input));
		}
	}

	// 게임결과
	public static int[] result(int[] result) {
		
		if (result[0] != 0 && result[1] != 0) {
			
			System.out.println(result[0] + " 스트라이크 " + result[1] + "볼");
		} else if (result[0] != 0 && result[1] == 0) {
			
			System.out.println(result[0] + " 스트라이크 ");
		} else if (result[0] == 0 && result[1] != 0) {
			
			System.out.println(result[1] + "볼");
		} else {
			
			System.out.println("낫싱");
		}
		
		return result;
	}

	// 입력값 유효성 검사
	public static int check(String input) {

		if (input.length() != NUMBER_LENGTH) {

			System.out.println("오류 // 입력값은 3자리수여야 합니다.");
			return -1;
		}

		if (checkDup(input) < 0) {

			System.out.println("오류 // 입력값에 중복된 숫자가 있습니다.");
			return -1;
		}

		return 0;
	}

	// 입력 값 중복 검사
	public static int checkDup(String input) {

		int dupCnt = 0;
		
		for(int i=0; i<input.length(); i++){
			
			dupCnt = comparePos(input, i, dupCnt);
		}
		
		if(dupCnt > 0) {
			
			return -1;
		}

		return 0;
	}
	
	// 생성된 번호 자리수 비교
	public static int comparePos(String input, int i, int dupCnt) {
		
		for(int j=i+1; j<input.length(); j++) {
			
			if(input.charAt(i) == input.charAt(j)) {
				
				dupCnt += 1;
			}
		}
		
		return dupCnt;
	}
	
	// 랜덤 숫자 3자리수 생성
	public static String setNum(String num) {

		int randomNum = 0;

		while (num.length() < NUMBER_LENGTH) {
			
			randomNum = (int) (Math.random() * 9) + 1; // 1 ~ 9 사이의 랜덤 숫자
			num += String.valueOf(randomNum);
			
			if(checkDup(num) < 0) {
				num = "";
				continue;
			}
		}

		return num;
	}

	// 생성된 번호와 입력한 번호 비교
	public static int[] compareNum(String num1, String num2) {

		int[] result = new int[2];
		int strikeOrBall = 0;
		
		for (int i = 0; i < NUMBER_LENGTH; i++) {
			
			strikeOrBall = strikeOrBall(num1, num2, i);
			
			if(strikeOrBall == 1) {			// 스트라이크
				
				result[0] += 1;
			}else if(strikeOrBall == 2) {	// 볼
				
				result[1] += 1;
			}
		}

		return result;
	}
	
	// 스트라이크 볼 개수 반환
	public static int strikeOrBall(String num1, String num2, int i) {
		
		int result = 0;
		
		for (int j = 0; j < NUMBER_LENGTH; j++) {
			
			if (num2.charAt(i) == num1.charAt(j)) {
				
				result = result(i, j);
			}
		}
		
		return result;
	}
	
	// 결과 합산
	public static int result(int i, int j) { 
		
		if (i == j) {	// 스트라이크
			
			return 1;
		} else {		// 볼
			
			return 2;
		}
	}
}
