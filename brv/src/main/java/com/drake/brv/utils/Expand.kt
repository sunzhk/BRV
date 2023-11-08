package com.drake.brv.utils

internal inline fun <reified R> Any.takeIfIs(): R? {
	return this.takeIf { this is R }?.let { this as R }
}

internal inline fun <reified R> Any.takeIfIs(block: (value: R) -> Unit): R? {
	return this.takeIf { this is R }?.let { 
		val r = this as R
		block.invoke(r)
		r
	}
}

