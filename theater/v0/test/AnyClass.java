package v0.test;

/**
 * 위의 코드에는 제일 큰 문제는
 * '코드 중복'이 발생할 확률이 높다는 것이다.
 * 다른 곳에서도 사각형의 너비와 높이를 증가시키는 코드가 필요하다면 
 * 아마 그곳에도 getRight와 getBottom 메서드를 호출해서 right와 bottom을 
 * 가져온 후 수정자 메서드를 이용해 값을 설정하는 유사한 코드가 존재할 것이다.
 */
public class AnyClass {
    void anyMethod(Rectangle rectangle, int multiple) {
        rectangle.setBottom(rectangle.getBottom() * multiple);
        rectangle.setRight(rectangle.getRight() * multiple);
    }
}
