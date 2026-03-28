package ch08_shopping_system.after.apec.payment_order_point.point;

import ch08_shopping_system.after.apec.payment_order_point.common.User;
public class PointService {
    
    public void usePoint(User user, int point) {
        // 포인트 사용 로직
        user.decreasePoint(point);
    }

    public void chargePoint(User user, int point) {
        // 최대 포인트 충전 방지에 대한 에러 처리
        user.checkMaxPoint(point);
        
        // 포인트 충전 로직
        user.increasePoint(point);
    }

}
