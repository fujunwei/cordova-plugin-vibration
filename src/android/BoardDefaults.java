/*
 * Copyright (c) 2017 Intel Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.cordova.vibration;

import android.os.Build;

// import com.google.android.things.pio.PeripheralManagerService;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class BoardDefaults {
    private static final String DEVICE_EDISON_ARDUINO = "edison_arduino";
    private static final String DEVICE_EDISON = "edison";
    private static final String DEVICE_JOULE = "joule";
    private static final String DEVICE_RPI3 = "rpi3";
    private static final String DEVICE_PICO = "imx6ul_pico";
    private static final String DEVICE_VVDN = "imx6ul_iopb";
    private static String sBoardVariant = "";

    public static String getPwmPin() {
        switch (getBoardVariant()) {
            case DEVICE_EDISON_ARDUINO:
                return "IO3";
            case DEVICE_EDISON:
                return "GP13";
            case DEVICE_JOULE:
                return "PWM_0";
            case DEVICE_RPI3:
                return "PWM1";
            case DEVICE_PICO:
                return "PWM7";
            case DEVICE_VVDN:
                return "PWM3";
            default:
                throw new UnsupportedOperationException("Unknown device: " + Build.DEVICE);
        }
    }

    private static String getBoardVariant() {
        if (!sBoardVariant.isEmpty()) {
            return sBoardVariant;
        }
        sBoardVariant = Build.DEVICE;
        // For the edison check the pin prefix
        // to always return Edison Breakout pin name when applicable.
        if (sBoardVariant.equals(DEVICE_EDISON)) {
            // BUGBUG:: fujunwei
            // PeripheralManagerService pioService = new PeripheralManagerService();
            // List<String> gpioList = pioService.getGpioList();
            // if (gpioList.size() != 0) {
            //     String pin = gpioList.get(0);
            //     if (pin.startsWith("IO")) {
            //         sBoardVariant = DEVICE_EDISON_ARDUINO;
            //     }
            // }
        }
        return sBoardVariant;
    }
}
