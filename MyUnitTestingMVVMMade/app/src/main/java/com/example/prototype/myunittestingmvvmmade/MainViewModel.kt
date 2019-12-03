package com.example.prototype.myunittestingmvvmmade

class MainViewModel(private val cuboidModel: CuboidModel) {
    fun gerCircumreference(): Double = cuboidModel.getCircumreference()

    fun getSurfaceArea(): Double = cuboidModel.getSurfaceAre()

    fun getVolume(): Double = cuboidModel.getVolume()

    fun save(l: Double, w: Double, h: Double) {
        cuboidModel.save(l,w,h)
    }
}