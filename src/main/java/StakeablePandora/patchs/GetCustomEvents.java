package StakeablePandora.patchs;

import basemod.eventUtil.EventUtils;
import basemod.eventUtil.util.ConditionalEvent;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.PandorasBox;

@SpirePatch(
        clz = PandorasBox.class,
        method = "onEquip"
)
public class GetCustomEvents {
    @SpireInsertPatch (rloc=14,localvars={"card"})
    public static SpireReturn prefix(PandorasBox instance, AbstractCard card) {
        CardModifierManager.addModifier(card, new StarterModifier());
        return SpireReturn.Continue();
    }
}