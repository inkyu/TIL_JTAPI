package javax.telephony.phone;

import javax.telephony.InvalidArgumentException;

public abstract interface PhoneDisplay extends Component {
	public abstract int getDisplayRows();

	public abstract int getDisplayColumns();

	public abstract String getDisplay(int paramInt1, int paramInt2)
			throws InvalidArgumentException;

	public abstract void setDisplay(String paramString, int paramInt1,
			int paramInt2) throws InvalidArgumentException;
}