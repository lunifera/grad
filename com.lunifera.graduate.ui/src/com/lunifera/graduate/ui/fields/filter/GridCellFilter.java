package com.lunifera.graduate.ui.fields.filter;

import org.vaadin.gridutil.cell.CellFilterComponent;

import com.vaadin.addon.jpacontainer.filter.JoinFilter;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class GridCellFilter extends org.vaadin.gridutil.cell.GridCellFilter {

	public GridCellFilter(Grid grid) {
		super(grid);
	}

	public TextField setEntityFilter(final Object columnId,
			final Class<?> entityClass, final String inputPrompt) {
		CellFilterComponent<TextField> filter = new CellFilterComponent<TextField>() {

			private static final long serialVersionUID = 1L;
			TextField textField = new TextField();

			@Override
			public TextField layoutComponent() {
				this.textField.setImmediate(true);
				this.textField.setInputPrompt(inputPrompt);
				this.textField.addStyleName(ValoTheme.TEXTFIELD_TINY);
				this.textField.addTextChangeListener(new TextChangeListener() {

					private static final long serialVersionUID = -3567212620627878001L;

					@Override
					public void textChange(final TextChangeEvent event) {
						if (event.getText() != null
								&& event.getText().length() > 0) {

							SimpleStringFilter numberFilter = new SimpleStringFilter(
									"number", event.getText(), true, false);
							SimpleStringFilter descriptionFilter = new SimpleStringFilter(
									"description", event.getText(), true, false);
							Or or = new Or(numberFilter, descriptionFilter);
							replaceFilter(
									new JoinFilter((String) columnId, or),
									columnId);
						} else {
							removeFilter(columnId);
						}
					}
				});
				return this.textField;
			}

			@Override
			public void clearFilter() {
				this.textField.clear();
			}
		};
		setCustomFilter(columnId, filter);
		return filter.getComponent();
	}

}
