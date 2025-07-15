package me.seun.fcfscouponserver

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

class CouponServiceTest {

    val couponService = CouponService()

    @Test
    fun issueCoupon_multiThread() {
        val clients = 100
        val threadPool = Executors.newFixedThreadPool(10)
        val startLatch = CountDownLatch(1)
        val doneLatch = CountDownLatch(clients)
        val maxCouponCount = 20
        couponService.maxCouponCount = maxCouponCount

        repeat(clients) {
            threadPool.submit {
                startLatch.await()
                try {
                    couponService.issueCoupon1()
                } finally {
                    doneLatch.countDown()
                }
            }
        }

        startLatch.countDown()
        doneLatch.await()
        println(couponService.issuedCoupon)
        assertThat(couponService.issuedCoupon).isEqualTo(maxCouponCount)
    }
}