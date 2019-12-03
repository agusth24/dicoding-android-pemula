package com.example.prototype.myunittestingmvvmmade

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.*

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var cuboidModel: CuboidModel

    private val dLength = 12.0
    private val dWidth = 7.0
    private val dHeight = 6.0

    private val dVolume = 504.0
    private val dCircumreference = 100.0
    private val dSurfaceArea = 396.0

    @Before
    fun before() {
        cuboidModel = mock(CuboidModel::class.java)
        mainViewModel = MainViewModel(cuboidModel)
    }

    @Test
    fun gerCircumreference() {
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dWidth,dLength,dHeight)
        val circumreference = mainViewModel.gerCircumreference()
        assertEquals(dCircumreference, circumreference, 0.00001)
    }

    @Test
    fun getSurfaceArea() {
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dWidth,dLength,dHeight)
        val surfacearea = mainViewModel.getSurfaceArea()
        assertEquals(dSurfaceArea, surfacearea, 0.00001)
    }

    @Test
    fun getVolume() {
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dWidth,dLength,dHeight)
        val volume = mainViewModel.getVolume()
        assertEquals(dVolume, volume,0.00001)
    }

    @Test
    fun save() {
    }

    @Test
    fun mockVolume() {
        `when`(mainViewModel.getVolume()).thenReturn(dVolume)
        val volume = mainViewModel.getVolume()
        verify(cuboidModel).getVolume()
        assertEquals(dVolume, volume,0.00001)
    }

    @Test
    fun mockCircumreference() {
        `when`(mainViewModel.gerCircumreference()).thenReturn(dCircumreference)
        val circumreference = mainViewModel.gerCircumreference()
        verify(cuboidModel).getCircumreference()
        assertEquals(dCircumreference, circumreference,0.00001)
    }

    @Test
    fun mockSurfaceArea() {
        `when`(mainViewModel.getSurfaceArea()).thenReturn(dSurfaceArea)
        val surfacearea = mainViewModel.getSurfaceArea()
        verify(cuboidModel).getSurfaceAre()
        assertEquals(dSurfaceArea, surfacearea,0.00001)
    }
}