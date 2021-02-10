/*******************************************************************************
 * Copyright (c) 2020 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package com.redhat.devtools.intellij.analytics.lang;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.EditorNotificationPanel;
import com.intellij.ui.EditorNotifications;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AnalyticsEditorNotificationProvider extends EditorNotifications.Provider<EditorNotificationPanel>{
    private static final Key<EditorNotificationPanel> KEY = Key.create("analytics.stackreport");

    @NotNull
    @Override
    public Key<EditorNotificationPanel> getKey() {
        return KEY;
    }

    @Nullable
    @Override
    public EditorNotificationPanel createNotificationPanel(@NotNull VirtualFile file, @NotNull FileEditor fileEditor) {
        return isSupportedForFile(file)?createdPanel():null;
    }

    private EditorNotificationPanel createdPanel() {
        EditorNotificationPanel panel = new EditorNotificationPanel();
        panel.setText("Dependency Analytics Report");
        panel.createActionLabel("Show report", () ->{

        });
        return panel;
    }

    private boolean isSupportedForFile(VirtualFile file) {
        return file != null && ("pom.xml".equals(file.getName()) || "package.json".equals(file.getName()) ||
                "requirement.txt".equals(file.getName()) || "go.mod".equals(file.getName()));
    }
}
