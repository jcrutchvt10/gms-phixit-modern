package com.gmsphixit.app.core.shell

import com.topjohnwu.superuser.Shell

@Suppress("DEPRECATION")
object InitShell {
    private const val SHELL_TIMEOUT = 10L
    private val shellConfig = Shell.Builder.create()
        .setFlags(Shell.FLAG_REDIRECT_STDERR or Shell.FLAG_MOUNT_MASTER)
        .setTimeout(SHELL_TIMEOUT)

    fun initShell() {
        try {
            Shell.setDefaultBuilder(shellConfig)
        } catch (_: IllegalStateException) {
        }
    }
}
