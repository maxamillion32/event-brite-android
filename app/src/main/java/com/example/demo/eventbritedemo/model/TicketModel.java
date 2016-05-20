package com.example.demo.eventbritedemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class TicketModel implements Parcelable {

    private String resource_uri;
    private String id;
    private String name;
    private String description;
    private boolean donation;
    private boolean free;
    private int minimum_quantity;
    private int maximum_quantity;
    private int maximum_quantity_per_order;
    private String on_sale_status;
    private int quantity_total;
    private int quantity_sold;
    private String sales_start;
    private String sales_end;
    private boolean hidden;
    private boolean include_fee;
    private boolean split_fee;
    private boolean hide_description;
    private boolean auto_hide;
    private boolean has_pdf_ticket;
    private String event_id;
    private List<String> variants;

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDonation() {
        return donation;
    }

    public void setDonation(boolean donation) {
        this.donation = donation;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getMinimum_quantity() {
        return minimum_quantity;
    }

    public void setMinimum_quantity(int minimum_quantity) {
        this.minimum_quantity = minimum_quantity;
    }

    public int getMaximum_quantity() {
        return maximum_quantity;
    }

    public void setMaximum_quantity(int maximum_quantity) {
        this.maximum_quantity = maximum_quantity;
    }

    public int getMaximum_quantity_per_order() {
        return maximum_quantity_per_order;
    }

    public void setMaximum_quantity_per_order(int maximum_quantity_per_order) {
        this.maximum_quantity_per_order = maximum_quantity_per_order;
    }

    public String getOn_sale_status() {
        return on_sale_status;
    }

    public void setOn_sale_status(String on_sale_status) {
        this.on_sale_status = on_sale_status;
    }

    public int getQuantity_total() {
        return quantity_total;
    }

    public void setQuantity_total(int quantity_total) {
        this.quantity_total = quantity_total;
    }

    public int getQuantity_sold() {
        return quantity_sold;
    }

    public void setQuantity_sold(int quantity_sold) {
        this.quantity_sold = quantity_sold;
    }

    public String getSales_start() {
        return sales_start;
    }

    public void setSales_start(String sales_start) {
        this.sales_start = sales_start;
    }

    public String getSales_end() {
        return sales_end;
    }

    public void setSales_end(String sales_end) {
        this.sales_end = sales_end;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isInclude_fee() {
        return include_fee;
    }

    public void setInclude_fee(boolean include_fee) {
        this.include_fee = include_fee;
    }

    public boolean isSplit_fee() {
        return split_fee;
    }

    public void setSplit_fee(boolean split_fee) {
        this.split_fee = split_fee;
    }

    public boolean isHide_description() {
        return hide_description;
    }

    public void setHide_description(boolean hide_description) {
        this.hide_description = hide_description;
    }

    public boolean isAuto_hide() {
        return auto_hide;
    }

    public void setAuto_hide(boolean auto_hide) {
        this.auto_hide = auto_hide;
    }

    public boolean isHas_pdf_ticket() {
        return has_pdf_ticket;
    }

    public void setHas_pdf_ticket(boolean has_pdf_ticket) {
        this.has_pdf_ticket = has_pdf_ticket;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public List<String> getVariants() {
        return variants;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resource_uri);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeByte(this.donation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.free ? (byte) 1 : (byte) 0);
        dest.writeInt(this.minimum_quantity);
        dest.writeInt(this.maximum_quantity);
        dest.writeInt(this.maximum_quantity_per_order);
        dest.writeString(this.on_sale_status);
        dest.writeInt(this.quantity_total);
        dest.writeInt(this.quantity_sold);
        dest.writeString(this.sales_start);
        dest.writeString(this.sales_end);
        dest.writeByte(this.hidden ? (byte) 1 : (byte) 0);
        dest.writeByte(this.include_fee ? (byte) 1 : (byte) 0);
        dest.writeByte(this.split_fee ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hide_description ? (byte) 1 : (byte) 0);
        dest.writeByte(this.auto_hide ? (byte) 1 : (byte) 0);
        dest.writeByte(this.has_pdf_ticket ? (byte) 1 : (byte) 0);
        dest.writeString(this.event_id);
        dest.writeStringList(this.variants);
    }

    public TicketModel() {
    }

    protected TicketModel(Parcel in) {
        this.resource_uri = in.readString();
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.donation = in.readByte() != 0;
        this.free = in.readByte() != 0;
        this.minimum_quantity = in.readInt();
        this.maximum_quantity = in.readInt();
        this.maximum_quantity_per_order = in.readInt();
        this.on_sale_status = in.readString();
        this.quantity_total = in.readInt();
        this.quantity_sold = in.readInt();
        this.sales_start = in.readString();
        this.sales_end = in.readString();
        this.hidden = in.readByte() != 0;
        this.include_fee = in.readByte() != 0;
        this.split_fee = in.readByte() != 0;
        this.hide_description = in.readByte() != 0;
        this.auto_hide = in.readByte() != 0;
        this.has_pdf_ticket = in.readByte() != 0;
        this.event_id = in.readString();
        this.variants = in.createStringArrayList();
    }

    public static final Parcelable.Creator<TicketModel> CREATOR = new Parcelable
            .Creator<TicketModel>() {
        @Override
        public TicketModel createFromParcel(Parcel source) {
            return new TicketModel(source);
        }

        @Override
        public TicketModel[] newArray(int size) {
            return new TicketModel[size];
        }
    };
}
