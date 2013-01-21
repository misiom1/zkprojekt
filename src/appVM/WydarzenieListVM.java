package appVM;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import domain.Wydarzenie;
import appData.WydarzeniaData;

public class WydarzenieListVM {

    private List<Wydarzenie> wydarzenieList = new ArrayList<Wydarzenie>();
    private Wydarzenie curSelectedWydarzenie;
    private Integer curSelectedWydarzenieIndex;
	public ListModel<Wydarzenie> getWydarzenieModel() {
		return new ListModelList<Wydarzenie>(wydarzenieList);
	}
    public Integer getCurSelectedWydarzenieIndex() {
		return curSelectedWydarzenieIndex;
	}

	public void setCurSelectedWydarzenieIndex(Integer curSelectedWydarzenieIndex) {
		this.curSelectedWydarzenieIndex = curSelectedWydarzenieIndex;
	}

	public Wydarzenie getCurSelectedWydarzenie() {
        return curSelectedWydarzenie;
    }

    public void setCurSelectedWydarzenie(Wydarzenie curSelectedWydarzenie) {
        this.curSelectedWydarzenie = curSelectedWydarzenie;
    }
    @Init
    public void initSetup() {
    	new WydarzeniaData();
		wydarzenieList = WydarzeniaData.getAllWydarzenia();
    }

    public List<Wydarzenie> getallWydarzenia() {
        return wydarzenieList;
    }

    @Command
    public void addNewWydarzenie() {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sWydarzenie", null);
        map.put("recordMode", "NEW");
        Executions.createComponents("WydarzenieCRUD.zul", null, map);
    }

    @Command
    public void editThisWydarzenie() {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sWydarzenie", this.curSelectedWydarzenie);
        map.put("recordMode", "EDIT");
        setCurSelectedWydarzenieIndex(wydarzenieList.indexOf(curSelectedWydarzenie));
        Executions.createComponents("WydarzenieCRUD.zul", null, map);
    }


    @GlobalCommand
    @NotifyChange("allWydarzenia")
    public void updateWydarzenieList(@BindingParam("pWydarzenie") Wydarzenie cust1,
            @BindingParam("recordMode") String recordMode) {
        if (recordMode.equals("NEW")) {
        	wydarzenieList.add(cust1);
        }
        if (recordMode.equals("EDIT")) {
            wydarzenieList.set(this.curSelectedWydarzenieIndex, cust1);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Command
    public void deleteThisWydarzenie() {

        String str = "Wydarzenie \""
                + curSelectedWydarzenie.getEvent()
                + "\" bêdzie usuniête.";

        Messagebox.show(str, "Confirm", Messagebox.OK | Messagebox.CANCEL,
                Messagebox.QUESTION, new EventListener() {
                    @Override
                    public void onEvent(Event event) throws Exception {
                        if (((Integer) event.getData()).intValue() == Messagebox.OK) {
                        	wydarzenieList.remove(wydarzenieList
                                    .indexOf(curSelectedWydarzenie));
                            BindUtils.postNotifyChange(null, null,
                                    WydarzenieListVM.this, "allWydarzenia");
                        }
                    }
                });
    }

}
