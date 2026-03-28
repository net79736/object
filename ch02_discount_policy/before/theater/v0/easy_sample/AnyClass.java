package ch02_discount_policy.before.theater.v0.easy_sample;

public class AnyClass {
    void anyMethodV1(Rectangle rectangle, int multiple) {
        // 중복 코드 발생할 가능성 ↑↑↑
        rectangle.setBottom(rectangle.getBottom() * multiple);
        rectangle.setRight(rectangle.getRight() * multiple);
    }

    void anyMethodV2(Rectangle rectangle, int multiple) {
        // 중복 코드 발생할 가능성 ↓↓↓
        rectangle.enlarge(multiple);
    }
}
