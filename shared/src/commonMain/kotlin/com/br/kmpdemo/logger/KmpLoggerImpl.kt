package com.br.kmpdemo.logger

import co.touchlab.kermit.Logger

class KmpLoggerImpl: KmpLogger {
    override fun i(tag: String, message: String) {
        Logger.withTag(tag).i(message)
    }

    override fun d(tag: String, message: String) {
        // TODO NEED TO ADD A DEBUG BUILD VARIANT CHECK HERE
        Logger.withTag(tag).d(message)
    }

    override fun e(tag: String, message: String) {
        Logger.withTag(tag).e(message)
    }
}