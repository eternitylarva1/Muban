//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StakeablePandora.patchs;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import java.util.ArrayList;
import java.util.Iterator;

public class PandorasBox extends AbstractRelic {
    public static final String ID = "Pandora's Box";
    private int count = 0;
    private boolean calledTransform = true;

    public PandorasBox() {
        super("Pandora's Box1", "pandoras_box.png", RelicTier.BOSS, LandingSound.MAGICAL);
        this.removeStrikeTip();
    }

    private void removeStrikeTip() {
        ArrayList<String> strikes = new ArrayList();

        for(String s : GameDictionary.STRIKE.NAMES) {
            strikes.add(s.toLowerCase());
        }

        Iterator<PowerTip> t = this.tips.iterator();

        while(t.hasNext()) {
            PowerTip derp = (PowerTip)t.next();
            String s = derp.header.toLowerCase();
            if (strikes.contains(s)) {
                t.remove();
                break;
            }
        }

    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onEquip() {
        this.calledTransform = false;
        Iterator<AbstractCard> i = AbstractDungeon.player.masterDeck.group.iterator();

        while(i.hasNext()) {
            AbstractCard e = (AbstractCard)i.next();
            if (e.hasTag(CardTags.STARTER_DEFEND) || e.hasTag(CardTags.STARTER_STRIKE)) {
                i.remove();
                ++this.count;
            }
        }

        if (this.count > 0) {
            CardGroup group = new CardGroup(CardGroupType.UNSPECIFIED);

            for(int j = 0; j < this.count; ++j) {
                AbstractCard card = AbstractDungeon.returnTrulyRandomCard().makeCopy();
                UnlockTracker.markCardAsSeen(card.cardID);
                card.isSeen = true;
                CardModifierManager.addModifier(card, new StarterModifier());
                for(AbstractRelic r : AbstractDungeon.player.relics) {
                    r.onPreviewObtainCard(card);
                }

                group.addToBottom(card);
            }

            AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, this.DESCRIPTIONS[1]);
        }

    }

    public void update() {
        super.update();
        if (!this.calledTransform && AbstractDungeon.screen != CurrentScreen.GRID) {
            this.calledTransform = true;
            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
        }

    }

    public AbstractRelic makeCopy() {
        return new PandorasBox();
    }
}
