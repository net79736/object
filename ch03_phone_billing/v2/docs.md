Phone에 taxRate 변수를 추가했고 두 변수의 값을 초기화하는 생성자를 추가했다.
이로 인해 Phone의 자식 클래스인 RegularPhone과 NightlyDiscountPhone의 생성자 역시 taxRate를 초기화 해주어야 한다.
책임을 아무리 잘 분리하더라도 인스턴스 변수의 추가는 상속 계층 전반에 변경을 유발 한다.
