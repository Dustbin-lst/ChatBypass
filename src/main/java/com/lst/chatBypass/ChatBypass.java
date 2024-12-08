package com.lst.chatBypass;

import today.opai.api.Extension;
import today.opai.api.OpenAPI;
import today.opai.api.annotations.ExtensionInfo;
import com.lst.chatBypass.modules.Bypass;

// Required @ExtensionInfo annotation
@ExtensionInfo(name = "ChatBypass",author = "lesetong",version = "1.0")
public class ChatBypass extends Extension {
    public static OpenAPI openAPI;

    @Override
    public void initialize(OpenAPI openAPI) {
        ChatBypass.openAPI = openAPI;
        // Modules
        openAPI.registerFeature(new Bypass());
    }
}
