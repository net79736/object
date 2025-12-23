package apec.final_prjct.repository.condition;

import java.util.List;

import apec.final_prjct.domain.Order;
import apec.final_prjct.repository.condition.dto.ProductSale;
import apec.final_prjct.repository.condition.intf.DiscountCondition;

/**
 * 상품 할인 조건
 * 할인 상품의 경우 할인 금액을 적용합니다.
 * 
 * @author 이종욱
 * @version 1.0
 * @since 2025-12-23
 */
public class ProductDiscountCondition implements DiscountCondition{
    private final List<ProductSale> productSales;

    public ProductDiscountCondition(List<ProductSale> productSales) {
        this.productSales = productSales;
    }

    @Override
    public boolean isSatisfied(Order order) {
        return false;
    }
}
