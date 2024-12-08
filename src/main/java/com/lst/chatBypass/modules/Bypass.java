package com.lst.chatBypass.modules;

import today.opai.api.enums.EnumModuleCategory;
import today.opai.api.events.EventPacketSend;
import today.opai.api.features.ExtensionModule;
import today.opai.api.interfaces.EventHandler;
import today.opai.api.interfaces.game.network.client.CPacket01Chat;
import today.opai.api.interfaces.modules.values.ModeValue;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import static com.lst.chatBypass.ChatBypass.openAPI;

public class Bypass extends ExtensionModule implements EventHandler {
    public Bypass() {
        super("ChatBypass", "Bypass chat filter", EnumModuleCategory.MISC);
        setEventHandler(this);
        super.addValues(mode);
    }
    private final ModeValue mode=openAPI.getValueManager().createModes("Mode","To shit char", Arrays.asList("Default","To shit char").toArray(new String[0]));
    @Override
    public void onPacketSend(EventPacketSend event) {
        if(!(event.getPacket() instanceof CPacket01Chat))return;
        String message= ((CPacket01Chat) event.getPacket()).getMessage();
        if (message.startsWith("/")||message.startsWith(".")) return;
        StringBuilder sb=new StringBuilder();
        for (char c : message.toCharArray()) {
            switch (mode.getValue()) {
                case "Both":
                case "To shit char":
                    try {
                        String field1 = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789";
                        String field2 = "ｑｗｅｒｔｙｕｉｏｐａｓｄｆｇｈｊｋｌｚｘｃｖｂｎｍｑｗｅｒｔｙｕｉｏｐａｓｄｆｇｈｊｋｌｚｘｃｖｂｎｍ０１２３４５６７８９";
                        sb.append(field2.charAt(field1.indexOf(c)));
                    } catch (Exception e) {
                        sb.append(c);
                    }
//                    if (Objects.equals(mode.getValue(), "Both")) {
//                        sb.append(generateRandomString((int) Math.floor(Math.random()*5+1)));
//                    }
                    break;
                default:
                    sb.append(c);
                    sb.append(generateRandomString((int) Math.floor(Math.random()*5+1)));
                    break;
            }
        }
        ((CPacket01Chat) event.getPacket()).setMessage(sb.length()>100? sb.substring(0,100):sb.toString());
    }
    private static String generateRandomString(int length) {
        String characters = "\uF8FF\u2069\u2066\u2067";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            stringBuilder.append(characters.charAt(index));
        }

        return stringBuilder.toString();
    }
}
