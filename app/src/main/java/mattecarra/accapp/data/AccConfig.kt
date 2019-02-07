package mattecarra.accapp.data

import mattecarra.accapp.AccUtils
import org.jetbrains.anko.doAsync

class Cooldown(charge: Int, pause: Int) {
    var charge: Int = charge
        set(value) {
            field = value
            updateAcc()
        }

    var pause: Int = pause
        set(value) {
            field = value
            updateAcc()
        }

    private fun updateAcc() {
        doAsync {
            synchronized(this@Cooldown) {
                AccUtils.updateCoolDown(charge, pause)
            }
        }
    }
}

class Capacity(shutdownCapacity: Int, coolDownCapacity: Int, resumeCapacity: Int, pauseCapacity: Int) {
    var shutdownCapacity: Int = shutdownCapacity
        set(value) {
            field = value
            updateAcc()
        }

    var coolDownCapacity: Int = coolDownCapacity
        set(value) {
            field = value
            updateAcc()
        }

    var resumeCapacity: Int = resumeCapacity
        set(value) {
            field = value
            updateAcc()
        }

    var pauseCapacity: Int = pauseCapacity
        set(value) {
            field = value
            updateAcc()
        }

    private fun updateAcc() {
        doAsync {
            synchronized(this@Capacity) {
                AccUtils.updateCapacity(shutdownCapacity, coolDownCapacity, resumeCapacity, pauseCapacity)
            }
        }
    }
}

class Temp(coolDownTemp: Int, pauseChargingTemp: Int, waitSeconds: Int) {
    var coolDownTemp: Int = coolDownTemp
        set(value) {
            field = value
            updateAcc()
        }

    var pauseChargingTemp: Int = pauseChargingTemp
        set(value) {
            field = value
            updateAcc()
        }

    var waitSeconds: Int = waitSeconds
        set(value) {
            field = value
            updateAcc()
        }

    private fun updateAcc() {
        doAsync {
            synchronized(this@Temp) {
                AccUtils.updateTemp(coolDownTemp, pauseChargingTemp, waitSeconds)
            }
        }
    }
}

class AccConfig(
    val capacity: Capacity,
    var cooldown: Cooldown?,
    val temp: Temp,
    resetUnplugged: Boolean,
    onBootExit: Boolean,
    onBoot: String?
) {

    var resetUnplugged: Boolean = resetUnplugged
        set(value) {
            field = value
            doAsync {
                synchronized(this@AccConfig) {
                    AccUtils.updateResetUnplugged(value)
                }
            }
        }

    var onBootExit: Boolean = onBootExit
        set(value) {
            field = value
            doAsync {
                synchronized(this@AccConfig) {
                    AccUtils.updateOnBootExit(value)
                }
            }
        }

    var onBoot: String? = onBoot
        set(value) {
            field = value
            doAsync {
                synchronized(this@AccConfig) {
                    AccUtils.updateOnBoot(onBoot)
                }
            }
        }
}