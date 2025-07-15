package me.seun.fcfscouponserver

import org.springframework.stereotype.Service

@Service
class CouponService {

    var issuedCoupon = 0
    var maxCouponCount = 0

    @Synchronized
    fun issueCoupon1() {
        if (issuedCoupon == maxCouponCount) {
            throw IllegalStateException("발행 가능한 쿠폰 수량이 없음")
        }
        Thread.sleep(1)
        issuedCoupon++
    }
}