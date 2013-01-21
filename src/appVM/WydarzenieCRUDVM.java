
package appVM;

import java.util.HashMap;
import java.util.Map;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;
import domain.Wydarzenie;

public class WydarzenieCRUDVM {

    /*
     * This is the window ID used in WydarzenieCRUD.Zul File. Using this only, we
     * can close the model window after save and cancel button
     */
    @Wire("#WydarzenieCRUD")
    private Window win;

    private Wydarzenie selectedWydarzenie;
    private String recordMode;

    public String getRecordMode() {
        return recordMode;
    }

    public void setRecordMode(String recordMode) {
        this.recordMode = recordMode;
    }

    public Wydarzenie getSelectedWydarzenie() {
        return selectedWydarzenie;
    }

    public void setSelectedWydarzenie(Wydarzenie selectedWydarzenie) {
        this.selectedWydarzenie = selectedWydarzenie;
    }

    @Init
    public void initSetup(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("sWydarzenie") Wydarzenie c1,
            @ExecutionArgParam("recordMode") String recordMode)
            throws CloneNotSupportedException {
        Selectors.wireComponents(view, this, false);

        setRecordMode(recordMode);
        if (recordMode.equals("NEW")) {
            this.selectedWydarzenie = new Wydarzenie();
        }

        if (recordMode.equals("EDIT")) {
            this.selectedWydarzenie = (Wydarzenie) c1.clone();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Command
    public void save() {
        Map args = new HashMap();
        args.put("pWydarzenie", this.selectedWydarzenie);
        args.put("recordMode", this.recordMode);
        BindUtils.postGlobalCommand(null, null, "updateWydarzenieList", args);
        win.detach();
    }

    @Command
    public void closeThis() {
        win.detach();
    }
}
