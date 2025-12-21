package apec.test5.point;

public class Point {
    private int point;

    public Point(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
    
    public void increase(int point) {
        this.point += point;
    }

    // 최대 포인트 충전 방지에 대한 에러 처리
    public void checkMaxPoint(int point) {
        if (point >= PointConstants.MAX_POINT - this.point) {
            throw new RuntimeException("최대 포인트 충전 방지입니다.");
        }
    }

    public void decrease(int point) {
        if (point > this.point) {
            throw new RuntimeException("포인트가 부족합니다. 현재 포인트: " + this.point + ", 감소할 포인트: " + point);
        }
        this.point -= point;
    }

}
