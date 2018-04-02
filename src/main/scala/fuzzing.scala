// Copyright 2018, The Regents of the University of California
// author: Kevin Laeufer <laeufer@cs.berkeley.edu>

// This file instanciates various SiFive peripherals so that we can fuzz test them.

package berkeley.fuzzing

import Chisel._

import sifive.blocks.devices.gpio._
import sifive.blocks.devices.spi._
import sifive.blocks.devices.uart._
import sifive.blocks.devices.i2c._

// class Uart(params: UARTParams) extends Module {

// 	val uarts = uartParams.zipWithIndex.map { case(params, i) =>
// 	val name = Some(s"uart_$i")
// 	val uart = LazyModule(new TLUART(pbus.beatBytes, params)).suggestName(name)
// 	pbus.toVariableWidthSlave(name) { uart.node }
// 	ibus.fromSync := uart.intnode
// 	uart
// 	}

// 	val dut = Module(new

// }

object HarnessGenerator extends App {
	val pbus_frequency = 115200 * 10
	val divinit = (pbus_frequency / 115200).toInt

	val params = new UARTParams(divisorInit = divinit)
	val beatBytes = 64 / 8

	chisel3.Driver.execute(args, () => new TLUART(beatBytes, params))
}