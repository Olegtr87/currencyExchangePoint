
package com.epam.vasilevsky.exchanger.webapp.page.password;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;

public class ModalWin extends ModalWindow {

    private boolean isCloseButton = false;

    public ModalWin(String id) {
        super(id);

        this.setInitialHeight(250);
        this.setInitialWidth(500);

        final ModalWin win = this;

        win.setPageCreator(new ModalWindow.PageCreator() {
            @Override
            public Page createPage() {
                return new ModalPage(win);
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
