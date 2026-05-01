package com.gmsphixit.app.presentation.feature.settings

import androidx.lifecycle.ViewModel
import com.gmsphixit.app.core.phixit.InitRootDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val rootDB: InitRootDB
) : ViewModel() {

    val dbVersion: Flow<Int?> = rootDB.databaseInitializationStateFlow.map { state ->
        if (state.isInitialized) {
            try { rootDB.getRootDatabase().getDbVersion() } catch (_: Exception) { -1 }
        } else null
    }
}
