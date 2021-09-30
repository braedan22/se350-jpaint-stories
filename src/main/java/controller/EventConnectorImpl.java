package controller;

import model.interfaces.UserChoices;
import view.EventName;
import view.interfaces.UiModule;

/**
 * @see controller.EventConnector
 */
public class EventConnectorImpl implements EventConnector {
    private final UiModule uiModule;
    private final UserChoices userChoices;

    public EventConnectorImpl(UiModule uiModule, UserChoices userChoices) {
        this.uiModule = uiModule;
        this.userChoices = userChoices;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> userChoices.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> userChoices.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> userChoices.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> userChoices.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> userChoices.setActiveStartAndEndPointMode());
    }
}
