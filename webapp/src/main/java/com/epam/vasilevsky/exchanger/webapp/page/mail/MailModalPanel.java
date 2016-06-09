package com.epam.vasilevsky.exchanger.webapp.page.mail;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.panel.Panel;

public final class MailModalPanel extends Panel {

    public MailModalPanel(final ModalWindow w) {
        super(w.getContentId());

        AjaxLink link = new AjaxLink("closeLink") {
            
            @Override
            public void onClick(AjaxRequestTarget target) {
                w.close(target);
            }
        };
        
        add(link);
    }
}
