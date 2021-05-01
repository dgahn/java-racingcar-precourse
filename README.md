# 자동차 경주 게임

이 프로젝트는 자동차 경주 Console 게임이다. 게임을 진행하는 순서는 다음과 같다.

게임을 시작하면 원하는 자동차의 갯수만큼 이름을 입력한다.

```
경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)
단군,동명왕,온조왕,혁거세
```

자동차들이 몇번 전진할지를 입력한다.
```
시도할 회수는 몇회인가요?
5
```

각 회차마다 다음과 같이 자동차는 0~9 사이의 점수를 얻게 되고 점수가 4이상인 경우 전진, 3이하인 경우 멈춘다. 
```
실행 결과
단군: 5
동명왕: 1
온조왕: 3
혁거세: 7
```

입력한 횟수만큼 실행결과가 나오고 마지막에 누가 우승했는지 결과가 나온다.
```
실행 결과
단군: 5
동명왕: 1
온조왕: 3
혁거세: 7

단군: 7
동명왕: 3
온조왕: 3
혁거세: 1

단군: 8
동명왕: 4
온조왕: 2
혁거세: 5

단군: 9
동명왕: 1
온조왕: 1
혁거세: 1

단군: 5
동명왕: 5
온조왕: 5
혁거세: 5

단군이 최종 우승했습니다.
```

## 기능 목록

- [X] 자동차 이름을 한번에 입력 받는다.
- [X] 자동차 이름을 입력 받으면 이를 쉼표(,)를 기준으로 나눈다. 단, 중간에 공백이 있는 경우 없앤다.
- [X] 자동차 이름은 5이하다.
- [ ] 각 자동차의 이름은 중복될 수 없다. 중복되면 각 자동차를 구별하기가 어렵다.
- [ ] 자동차 이름을 하나만 입력하면 에러가 발생한다.
- [ ] 게임 총 라운드를 0회 입력하면 에러가 발생한다.
- [X] 자동차가 몇회 움직일지 값을 입력 받는다. 
- [X] 입력 받는 값은 반드시 숫자다.
- [X] 자동차는 처음에 0이라는 위치를 갖는다.
- [X] 자동차는 0~9 사이의 숫자 매 회 부여 받는다. 
- [X] 자동차는 매 회 숫자를 부여 받고 4이상이면 위치 + 1 하고 3이하면 그 이전 위치를 가진다.
- [X] 자동차가 점수를 부여 받을 때마다 각 자동차가 몇 점을 부여 받았는지 실행결과로 출력이 가능하다.
- [X] 우승자를 뽑기 위해서는 매판마다 얻은 점수를 기록 해야 한다.
- [X] 자동차가 이동할 거리는 마지막에 한번에 계산한다.
- [X] 자동차 게임이 끝나면 우승한 자동차의 이름을 출력한다. 단, 우승자는 여러명일 수 있다.
