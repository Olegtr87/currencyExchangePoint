
package com.epam.vasilevsky.exchanger.webapp.page.mail;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;

public class MailModalWin extends ModalWindow {

    private boolean isCloseButton = false;

    public MailModalWin(String id) {
        super(id);

        this.setInitialHeight(500);
        this.setInitialWidth(500);

        final MailModalWin win = this;

        win.setPageCreator(new ModalWindow.PageCreator() {
            @Override
            public Page createPage() {
                return new MailModalPage(win);
            }
        });

        // simply closes window if click on corner X
        win.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
            @Override
            public boolean onCloseButtonClicked(AjaxRequestTarget target) {
                isCloseButton = true;
                return true;
            }
        });
    }

    @Override
    public String getContentId() {
        return "modalWin1";
    }
}
