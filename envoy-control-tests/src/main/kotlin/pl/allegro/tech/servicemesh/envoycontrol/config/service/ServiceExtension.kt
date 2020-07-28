package pl.allegro.tech.servicemesh.envoycontrol.config.service

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

open class ServiceExtension<T : ServiceContainer>(val container: T, private val shared: Boolean = false)
    : BeforeAllCallback, AfterAllCallback {

    var started = false

    override fun beforeAll(context: ExtensionContext) {
        if (started) {
            return
        }

        container.start()
        started = true
    }

    override fun afterAll(context: ExtensionContext) {
        if (!shared) {
            container.stop()
        }
    }
}
