package apec.test6.payment.intf;

import apec.test6.Order;
import common.Money;

/**
 * 결제 게이트웨이 인터페이스
 * 
 * 변하지 않는 부분: 결제를 처리한다는 행위 자체
 * 변하는 부분: 각 결제 수단의 구체적인 처리 방법
 * 
 * 이 인터페이스는 변하지 않는 부분(결제 처리 행위)을 추상화하고,
 * 각 구현체가 변하는 부분(구체적인 처리 방법)을 담당합니다.
 */
public interface PaymentGateway {
    /**
     * 주문에 대한 결제를 처리합니다.
     * 
     * @param order 결제할 주문
     * @param paymentInfo 결제에 필요한 정보 (결제 수단별로 다른 정보)
     * @param amount 결제 금액
     */
    void pay(Order order, PaymentInfo paymentInfo, Money amount);
}
