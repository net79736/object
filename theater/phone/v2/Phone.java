package phone.v2;

import java.util.ArrayList;
import java.util.List;
import common.Money;

public abstract class Phone {
    private double taxRate;
    private List<Call> calls = new ArrayList();

    public Phone(double taxRate) {
        this.taxRate = taxRate;
    }
    
    /**
     * 전체 통화 목록의 요금 계산
     * @return 전체 통화 목록의 요금
     */
    public Money calculateFee() {
        Money result = Money. ZERO;
    
        for(Call call : calls) {
            // 개별 통화 요금 계산
            result = result.plus(calculateCallFee(call));
        }
        
        // 세액 추가
        return result.plus(result.times(taxRate));
    }

    /**
     * 개별 통화 요금 계산
     * @param call
     * @return
     */
    protected abstract Money calculateCallFee (Call call);
}
