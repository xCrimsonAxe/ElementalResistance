package de.sadlife.elementalresistance.utils;

import java.util.Map;

import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.bukkit.entity.Player;

import de.sadlife.elementalresistance.ElementalResistance;

public class ResistanceDialog {


	enum Data {
		SLOT,
		NAME,
		FRESIST,
		WRESIST,
		LRESIST,
		SRESIST,
		LEVEL
	}

	public ResistanceDialog(Player p) {
		final Conversation conv = new ConversationFactory(ElementalResistance.getInstance())
		.withModality(true)
		.withPrefix(context -> Common.colorize("&8Resistance | &f"))
		.withTimeout(60)
		.withEscapeSequence("quit")
		.thatExcludesNonPlayersWithMessage("Only online players can create new items.")
		.addConversationAbandonedListener(e -> {
			final Conversable c = e.getContext().getForWhom();
			final Map<Object, Object> m = e.getContext().getAllSessionData();
			if(e.gracefulExit()) {
				c.sendRawMessage(Common.colorize("&aThe Item has been created successfully! " + m.get(Data.FRESIST)));
				p.getInventory().addItem(AdvancedItemStack.Resistance(m.get(Data.NAME), m.get(Data.SLOT), m.get(Data.FRESIST), m.get(Data.WRESIST), m.get(Data.LRESIST), m.get(Data.SRESIST), m.get(Data.LEVEL)));
			} else {
				c.sendRawMessage(Common.colorize("&cItem creation has been cancelled."));
			}


		})
		.withFirstPrompt(new SlotPrompt())
		.buildConversation(p);
		p.beginConversation(conv);
	}



	class SlotPrompt extends ValidatingPrompt {

		@Override
		public String getPromptText(ConversationContext context) {
			return Common.colorize("&ePlease enter the item's wearing slot, either 'legs' or 'feet'.");

		}

		@Override
		protected boolean isInputValid(ConversationContext context, String input) {
			return input.equalsIgnoreCase("legs") || input.equalsIgnoreCase("feet");
		}

		@Override
		protected String getFailedValidationText(ConversationContext context, String invalidInput) {
			return Common.colorize("&cPlease only enter 'legs' or 'feet or 'quit' to exit the conversation.");
		}

		@Override
		public Prompt acceptValidatedInput(ConversationContext context, String input) {
			input.toLowerCase();
			context.setSessionData(Data.SLOT, input);

			return new NamePrompt();
		}
	}


	class NamePrompt extends StringPrompt {

		@Override
		public String getPromptText(ConversationContext context) {
			return Common.colorize("&ePlease enter the item's name to the chat");

		}

		@Override
		public Prompt acceptInput(ConversationContext context, String input) {
			context.setSessionData(Data.NAME, input);

			return new FResistPrompt();
		}
	}



	class FResistPrompt extends NumericPrompt {

		@Override
		public String getPromptText(ConversationContext context) {
			return Common.colorize("&ePlease enter the fire resistance of &6" + Data.NAME + "&e to the chat.");

		}

		@Override
		protected boolean isNumberValid(ConversationContext context, Number input) {
			return input.intValue() >= 0 && input.intValue() <=10;
		}

		@Override
		public String getFailedValidationText(ConversationContext context, Number input) {
			return Common.colorize("&cPlease only enter a whole number between 0 and 10 here, or type 'quit' to exit.");
		}

		@Override
		public Prompt acceptValidatedInput(ConversationContext context, Number input) {
			context.setSessionData(Data.FRESIST, input);

			return new WResistPrompt();
		}
	}

	class WResistPrompt extends NumericPrompt {

		@Override
		public String getPromptText(ConversationContext context) {
			return Common.colorize("&ePlease enter the water resistance of &6" + Data.NAME + "&e to the chat.");

		}

		@Override
		protected boolean isNumberValid(ConversationContext context, Number input) {
			return input.intValue() >= 0 && input.intValue() <=10;
		}

		@Override
		public String getFailedValidationText(ConversationContext context, Number input) {
			return Common.colorize("&cPlease only enter a whole number between 0 and 10 here, or type 'quit' to exit.");
		}

		@Override
		public Prompt acceptValidatedInput(ConversationContext context, Number input) {
			context.setSessionData(Data.WRESIST, input);

			return new LResistPrompt();
		}
	}

	class LResistPrompt extends NumericPrompt {

		@Override
		public String getPromptText(ConversationContext context) {
			return Common.colorize("&ePlease enter the light resistance of &6" + Data.NAME + "&e to the chat.");

		}

		@Override
		protected boolean isNumberValid(ConversationContext context, Number input) {
			return input.intValue() >= 0 && input.intValue() <=10;
		}

		@Override
		public String getFailedValidationText(ConversationContext context, Number input) {
			return Common.colorize("&cPlease only enter a whole number between 0 and 10 here, or type 'quit' to exit.");
		}

		@Override
		public Prompt acceptValidatedInput(ConversationContext context, Number input) {
			context.setSessionData(Data.LRESIST, input);

			return new SResistPrompt();
		}
	}

	class SResistPrompt extends NumericPrompt {

		@Override
		public String getPromptText(ConversationContext context) {
			return Common.colorize("&ePlease enter the shadow resistance of &6" + Data.NAME + "&e to the chat.");

		}

		@Override
		protected boolean isNumberValid(ConversationContext context, Number input) {
			return input.intValue() >= 0 && input.intValue() <=10;
		}

		@Override
		public String getFailedValidationText(ConversationContext context, Number input) {
			return Common.colorize("&cPlease only enter a whole number between 0 and 10 here, or type 'quit' to exit.");
		}

		@Override
		public Prompt acceptValidatedInput(ConversationContext context, Number input) {
			context.setSessionData(Data.SRESIST, input);

			return new LevelPrompt();
		}
	}

	class LevelPrompt extends NumericPrompt {

		@Override
		public String getPromptText(ConversationContext context) {
			return Common.colorize("&ePlease enter the levelrequirement of &6" + Data.NAME.toString() + "&e to the chat.");

		}

		@Override
		protected boolean isNumberValid(ConversationContext context, Number input) {
			return input.intValue() >= 1 && input.intValue() <=99;
		}

		@Override
		public String getFailedValidationText(ConversationContext context, Number input) {
			return Common.colorize("&cPlease only enter a whole number between 1 and 99 here, or type 'quit' to exit.");
		}

		@Override
		public Prompt acceptValidatedInput(ConversationContext context, Number input) {
			context.setSessionData(Data.LEVEL, input);

			return END_OF_CONVERSATION;
		}
	}
}
