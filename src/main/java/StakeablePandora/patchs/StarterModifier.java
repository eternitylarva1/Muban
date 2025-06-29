package StakeablePandora.patchs;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class StarterModifier extends AbstractCardModifier {

    @Override
    public AbstractCardModifier makeCopy() {
        return new StarterModifier();
    }
    @Override
    public void onInitialApplication(AbstractCard card) {
        card.tags.add(AbstractCard.CardTags.STARTER_STRIKE);
    }

}
