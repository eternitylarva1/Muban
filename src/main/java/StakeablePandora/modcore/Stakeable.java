package StakeablePandora.modcore;


import basemod.*;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.PandorasBox;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


import static com.megacrit.cardcrawl.core.Settings.language;


@SpireInitializer
public class Stakeable implements PostInitializeSubscriber,EditKeywordsSubscriber,OnStartBattleSubscriber, StartActSubscriber , EditStringsSubscriber, EditRelicsSubscriber { // 实现接口
    public Stakeable() {
        BaseMod.subscribe(this); // 告诉basemod你要订阅事件
    }
    public static final String MyModID = "Muban";
    ModPanel settingsPanel = new ModPanel();
    public static SpireConfig config;
    public static void initialize() throws IOException {

        new Stakeable();

        config=new SpireConfig("StakeablePandora", "StakeablePandora");
        config.load();

    }

    // 当basemod开始注册mod卡牌时，便会调用这个函数

    @Override
    public void receiveStartAct() {

    }

    @Override
    public void receiveEditRelics() {
        
    }

    @Override
    public void receiveEditStrings() {
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
        }
   BaseMod.loadCustomStringsFile(RelicStrings.class, "StakeableResources/localization/" + lang + "/relics.json");

    }
    public static float getYPos(float y) {
        return Settings.HEIGHT/(2160/y);
    }
    public static float getXPos(float x) {
        return Settings.WIDTH/(3840/x);
    }
    @Override
    public void receivePostInitialize() {

    }



    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "ENG";
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }

        String json = Gdx.files.internal("StakeableResources/localization/" + lang + "/keywords.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        /*
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                // 这个id要全小写
                BaseMod.addKeyword("muban", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }*/
    }
}