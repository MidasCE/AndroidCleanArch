package com.example.app.main.mapscreen.presenter

import com.example.app.core.SchedulerFactory
import com.example.app.main.mapscreen.MapScreenView
import com.example.domain.interactor.PermissionInteractor
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MapScreenPresenterImplTest {

    @Mock
    lateinit var schedulerFactory: SchedulerFactory

    @Mock
    lateinit var view: MapScreenView

    @Mock
    lateinit var permissionInteractor: PermissionInteractor

    private lateinit var mapScreenPresenterImpl: MapScreenPresenterImpl

    private lateinit var ioScheduler: TestScheduler

    private lateinit var mainScheduler: TestScheduler

    @Before
    fun setUp() {
        ioScheduler = TestScheduler()
        mainScheduler = TestScheduler()

        whenever(schedulerFactory.io()).thenReturn(ioScheduler)
        whenever(schedulerFactory.main()).thenReturn(mainScheduler)

        mapScreenPresenterImpl = MapScreenPresenterImpl(schedulerFactory, view, permissionInteractor)
    }
}
