package com.example.demo.eventbritedemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class EventResponseModel {

    private PaginationEntity pagination;

    private List<EventsEntity> events;

    public PaginationEntity getPagination() {
        return pagination;
    }

    public void setPagination(PaginationEntity pagination) {
        this.pagination = pagination;
    }

    public List<EventsEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventsEntity> events) {
        this.events = events;
    }

    public static class PaginationEntity {
        private int object_count;
        private int page_number;
        private int page_size;
        private int page_count;

        public int getObject_count() {
            return object_count;
        }

        public void setObject_count(int object_count) {
            this.object_count = object_count;
        }

        public int getPage_number() {
            return page_number;
        }

        public void setPage_number(int page_number) {
            this.page_number = page_number;
        }

        public int getPage_size() {
            return page_size;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }

        public int getPage_count() {
            return page_count;
        }

        public void setPage_count(int page_count) {
            this.page_count = page_count;
        }
    }

    public static class EventsEntity implements Parcelable {

        private NameEntity name;
        private DescriptionEntity description;
        private String id;
        private String url;
        private String vanity_url;
        private StartEntity start;
        private EndEntity end;
        private String created;
        private String changed;
        private int capacity;
        private String status;
        private String currency;
        private boolean listed;
        private boolean shareable;
        private boolean online_event;
        private int tx_time_limit;
        private boolean hide_start_date;
        private String locale;
        private boolean is_locked;
        private String privacy_setting;
        private boolean is_series;
        private boolean is_series_parent;
        private boolean is_reserved_seating;
        private String logo_id;
        private String organizer_id;
        private String venue_id;
        private String category_id;
        private String subcategory_id;
        private String format_id;
        private String resource_uri;

        public List<TicketModel> getTicket_classes() {
            return ticket_classes;
        }

        public void setTicket_classes(List<TicketModel> ticket_classes) {
            this.ticket_classes = ticket_classes;
        }

        private List<TicketModel> ticket_classes;

        private LogoEntity logo;

        public NameEntity getName() {
            return name;
        }

        public void setName(NameEntity name) {
            this.name = name;
        }

        public DescriptionEntity getDescription() {
            return description;
        }

        public void setDescription(DescriptionEntity description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVanity_url() {
            return vanity_url;
        }

        public void setVanity_url(String vanity_url) {
            this.vanity_url = vanity_url;
        }

        public StartEntity getStart() {
            return start;
        }

        public void setStart(StartEntity start) {
            this.start = start;
        }

        public EndEntity getEnd() {
            return end;
        }

        public void setEnd(EndEntity end) {
            this.end = end;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getChanged() {
            return changed;
        }

        public void setChanged(String changed) {
            this.changed = changed;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public boolean isListed() {
            return listed;
        }

        public void setListed(boolean listed) {
            this.listed = listed;
        }

        public boolean isShareable() {
            return shareable;
        }

        public void setShareable(boolean shareable) {
            this.shareable = shareable;
        }

        public boolean isOnline_event() {
            return online_event;
        }

        public void setOnline_event(boolean online_event) {
            this.online_event = online_event;
        }

        public int getTx_time_limit() {
            return tx_time_limit;
        }

        public void setTx_time_limit(int tx_time_limit) {
            this.tx_time_limit = tx_time_limit;
        }

        public boolean isHide_start_date() {
            return hide_start_date;
        }

        public void setHide_start_date(boolean hide_start_date) {
            this.hide_start_date = hide_start_date;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public boolean isIs_locked() {
            return is_locked;
        }

        public void setIs_locked(boolean is_locked) {
            this.is_locked = is_locked;
        }

        public String getPrivacy_setting() {
            return privacy_setting;
        }

        public void setPrivacy_setting(String privacy_setting) {
            this.privacy_setting = privacy_setting;
        }

        public boolean isIs_series() {
            return is_series;
        }

        public void setIs_series(boolean is_series) {
            this.is_series = is_series;
        }

        public boolean isIs_series_parent() {
            return is_series_parent;
        }

        public void setIs_series_parent(boolean is_series_parent) {
            this.is_series_parent = is_series_parent;
        }

        public boolean isIs_reserved_seating() {
            return is_reserved_seating;
        }

        public void setIs_reserved_seating(boolean is_reserved_seating) {
            this.is_reserved_seating = is_reserved_seating;
        }

        public String getLogo_id() {
            return logo_id;
        }

        public void setLogo_id(String logo_id) {
            this.logo_id = logo_id;
        }

        public String getOrganizer_id() {
            return organizer_id;
        }

        public void setOrganizer_id(String organizer_id) {
            this.organizer_id = organizer_id;
        }

        public String getVenue_id() {
            return venue_id;
        }

        public void setVenue_id(String venue_id) {
            this.venue_id = venue_id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getSubcategory_id() {
            return subcategory_id;
        }

        public void setSubcategory_id(String subcategory_id) {
            this.subcategory_id = subcategory_id;
        }

        public String getFormat_id() {
            return format_id;
        }

        public void setFormat_id(String format_id) {
            this.format_id = format_id;
        }

        public String getResource_uri() {
            return resource_uri;
        }

        public void setResource_uri(String resource_uri) {
            this.resource_uri = resource_uri;
        }

        public LogoEntity getLogo() {
            return logo;
        }

        public void setLogo(LogoEntity logo) {
            this.logo = logo;
        }

        public static class NameEntity implements Parcelable {
            private String text;
            private String html;

            public NameEntity() {
            }

            public NameEntity(String html) {
                this.html = html;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getHtml() {
                return html;
            }

            public void setHtml(String html) {
                this.html = html;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.text);
                dest.writeString(this.html);
            }

            protected NameEntity(Parcel in) {
                this.text = in.readString();
                this.html = in.readString();
            }

            public static final Creator<NameEntity> CREATOR = new Creator<NameEntity>() {
                @Override
                public NameEntity createFromParcel(Parcel source) {
                    return new NameEntity(source);
                }

                @Override
                public NameEntity[] newArray(int size) {
                    return new NameEntity[size];
                }
            };
        }

        public static class DescriptionEntity implements Parcelable {
            private String text;
            private String html;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getHtml() {
                return html;
            }

            public void setHtml(String html) {
                this.html = html;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.text);
                dest.writeString(this.html);
            }

            public DescriptionEntity() {
            }

            protected DescriptionEntity(Parcel in) {
                this.text = in.readString();
                this.html = in.readString();
            }

            public static final Creator<DescriptionEntity> CREATOR = new
                    Creator<DescriptionEntity>() {
                        @Override
                        public DescriptionEntity createFromParcel(Parcel source) {
                            return new DescriptionEntity(source);
                        }

                        @Override
                        public DescriptionEntity[] newArray(int size) {
                            return new DescriptionEntity[size];
                        }
                    };
        }

        public static class StartEntity implements Parcelable {
            private String timezone;
            private String local;
            private String utc;

            public StartEntity(String timezone, String utc) {
                this.timezone = timezone;
                this.utc = utc;
            }

            public StartEntity(String utc) {
                this.utc = utc;
            }

            public StartEntity() {
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getLocal() {
                return local;
            }

            public void setLocal(String local) {
                this.local = local;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }

            @Override
            public String toString() {
                return getUtc();
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.timezone);
                dest.writeString(this.local);
                dest.writeString(this.utc);
            }

            protected StartEntity(Parcel in) {
                this.timezone = in.readString();
                this.local = in.readString();
                this.utc = in.readString();
            }

            public static final Creator<StartEntity> CREATOR = new Creator<StartEntity>() {
                @Override
                public StartEntity createFromParcel(Parcel source) {
                    return new StartEntity(source);
                }

                @Override
                public StartEntity[] newArray(int size) {
                    return new StartEntity[size];
                }
            };
        }

        public static class EndEntity implements Parcelable {
            private String timezone;
            private String local;
            private String utc;

            public EndEntity(String timezone, String utc) {
                this.timezone = timezone;
                this.utc = utc;
            }

            public EndEntity() {
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getLocal() {
                return local;
            }

            public void setLocal(String local) {
                this.local = local;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }

            @Override
            public String toString() {
                return getUtc();
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.timezone);
                dest.writeString(this.local);
                dest.writeString(this.utc);
            }

            protected EndEntity(Parcel in) {
                this.timezone = in.readString();
                this.local = in.readString();
                this.utc = in.readString();
            }

            public static final Creator<EndEntity> CREATOR = new Creator<EndEntity>() {
                @Override
                public EndEntity createFromParcel(Parcel source) {
                    return new EndEntity(source);
                }

                @Override
                public EndEntity[] newArray(int size) {
                    return new EndEntity[size];
                }
            };
        }

        public static class LogoEntity implements Parcelable {
            private String id;
            private String url;
            private String aspect_ratio;
            private String edge_color;
            private boolean edge_color_set;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getAspect_ratio() {
                return aspect_ratio;
            }

            public void setAspect_ratio(String aspect_ratio) {
                this.aspect_ratio = aspect_ratio;
            }

            public String getEdge_color() {
                return edge_color;
            }

            public void setEdge_color(String edge_color) {
                this.edge_color = edge_color;
            }

            public boolean isEdge_color_set() {
                return edge_color_set;
            }

            public void setEdge_color_set(boolean edge_color_set) {
                this.edge_color_set = edge_color_set;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.url);
                dest.writeString(this.aspect_ratio);
                dest.writeString(this.edge_color);
                dest.writeByte(this.edge_color_set ? (byte) 1 : (byte) 0);
            }

            public LogoEntity() {
            }

            protected LogoEntity(Parcel in) {
                this.id = in.readString();
                this.url = in.readString();
                this.aspect_ratio = in.readString();
                this.edge_color = in.readString();
                this.edge_color_set = in.readByte() != 0;
            }

            public static final Creator<LogoEntity> CREATOR = new Creator<LogoEntity>() {
                @Override
                public LogoEntity createFromParcel(Parcel source) {
                    return new LogoEntity(source);
                }

                @Override
                public LogoEntity[] newArray(int size) {
                    return new LogoEntity[size];
                }
            };
        }

        public EventsEntity() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.name, flags);
            dest.writeParcelable(this.description, flags);
            dest.writeString(this.id);
            dest.writeString(this.url);
            dest.writeString(this.vanity_url);
            dest.writeParcelable(this.start, flags);
            dest.writeParcelable(this.end, flags);
            dest.writeString(this.created);
            dest.writeString(this.changed);
            dest.writeInt(this.capacity);
            dest.writeString(this.status);
            dest.writeString(this.currency);
            dest.writeByte(this.listed ? (byte) 1 : (byte) 0);
            dest.writeByte(this.shareable ? (byte) 1 : (byte) 0);
            dest.writeByte(this.online_event ? (byte) 1 : (byte) 0);
            dest.writeInt(this.tx_time_limit);
            dest.writeByte(this.hide_start_date ? (byte) 1 : (byte) 0);
            dest.writeString(this.locale);
            dest.writeByte(this.is_locked ? (byte) 1 : (byte) 0);
            dest.writeString(this.privacy_setting);
            dest.writeByte(this.is_series ? (byte) 1 : (byte) 0);
            dest.writeByte(this.is_series_parent ? (byte) 1 : (byte) 0);
            dest.writeByte(this.is_reserved_seating ? (byte) 1 : (byte) 0);
            dest.writeString(this.logo_id);
            dest.writeString(this.organizer_id);
            dest.writeString(this.venue_id);
            dest.writeString(this.category_id);
            dest.writeString(this.subcategory_id);
            dest.writeString(this.format_id);
            dest.writeString(this.resource_uri);
            dest.writeTypedList(this.ticket_classes);
            dest.writeParcelable(this.logo, flags);
        }

        protected EventsEntity(Parcel in) {
            this.name = in.readParcelable(NameEntity.class.getClassLoader());
            this.description = in.readParcelable(DescriptionEntity.class.getClassLoader());
            this.id = in.readString();
            this.url = in.readString();
            this.vanity_url = in.readString();
            this.start = in.readParcelable(StartEntity.class.getClassLoader());
            this.end = in.readParcelable(EndEntity.class.getClassLoader());
            this.created = in.readString();
            this.changed = in.readString();
            this.capacity = in.readInt();
            this.status = in.readString();
            this.currency = in.readString();
            this.listed = in.readByte() != 0;
            this.shareable = in.readByte() != 0;
            this.online_event = in.readByte() != 0;
            this.tx_time_limit = in.readInt();
            this.hide_start_date = in.readByte() != 0;
            this.locale = in.readString();
            this.is_locked = in.readByte() != 0;
            this.privacy_setting = in.readString();
            this.is_series = in.readByte() != 0;
            this.is_series_parent = in.readByte() != 0;
            this.is_reserved_seating = in.readByte() != 0;
            this.logo_id = in.readString();
            this.organizer_id = in.readString();
            this.venue_id = in.readString();
            this.category_id = in.readString();
            this.subcategory_id = in.readString();
            this.format_id = in.readString();
            this.resource_uri = in.readString();
            this.ticket_classes = in.createTypedArrayList(TicketModel.CREATOR);
            this.logo = in.readParcelable(LogoEntity.class.getClassLoader());
        }

        public static final Creator<EventsEntity> CREATOR = new Creator<EventsEntity>() {
            @Override
            public EventsEntity createFromParcel(Parcel source) {
                return new EventsEntity(source);
            }

            @Override
            public EventsEntity[] newArray(int size) {
                return new EventsEntity[size];
            }
        };
    }
}
