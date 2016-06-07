package com.example.demo.eventbritedemo.model;

import java.util.List;

public class OrderModel {

    private PaginationEntity pagination;
    private List<OrdersEntity> orders;

    public PaginationEntity getPagination() {
        return pagination;
    }

    public void setPagination(PaginationEntity pagination) {
        this.pagination = pagination;
    }

    public List<OrdersEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersEntity> orders) {
        this.orders = orders;
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

    public static class OrdersEntity {

        private CostsEntity costs;
        private String resource_uri;
        private String id;
        private String changed;
        private String created;
        private String name;
        private String first_name;
        private String last_name;
        private String email;
        private String status;
        private Object time_remaining;
        private String event_id;
        private EventResponseModel.EventsEntity event;

        public EventResponseModel.EventsEntity getEvent() {
            return event;
        }

        public void setEvent(EventResponseModel.EventsEntity event) {
            this.event = event;
        }

        public CostsEntity getCosts() {
            return costs;
        }

        public void setCosts(CostsEntity costs) {
            this.costs = costs;
        }

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

        public String getChanged() {
            return changed;
        }

        public void setChanged(String changed) {
            this.changed = changed;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getTime_remaining() {
            return time_remaining;
        }

        public void setTime_remaining(Object time_remaining) {
            this.time_remaining = time_remaining;
        }

        public String getEvent_id() {
            return event_id;
        }

        public void setEvent_id(String event_id) {
            this.event_id = event_id;
        }

        public static class CostsEntity {

            private BasePriceEntity base_price;
            private EventbriteFeeEntity eventbrite_fee;
            private GrossEntity gross;
            private PaymentFeeEntity payment_fee;
            private TaxEntity tax;

            public BasePriceEntity getBase_price() {
                return base_price;
            }

            public void setBase_price(BasePriceEntity base_price) {
                this.base_price = base_price;
            }

            public EventbriteFeeEntity getEventbrite_fee() {
                return eventbrite_fee;
            }

            public void setEventbrite_fee(EventbriteFeeEntity eventbrite_fee) {
                this.eventbrite_fee = eventbrite_fee;
            }

            public GrossEntity getGross() {
                return gross;
            }

            public void setGross(GrossEntity gross) {
                this.gross = gross;
            }

            public PaymentFeeEntity getPayment_fee() {
                return payment_fee;
            }

            public void setPayment_fee(PaymentFeeEntity payment_fee) {
                this.payment_fee = payment_fee;
            }

            public TaxEntity getTax() {
                return tax;
            }

            public void setTax(TaxEntity tax) {
                this.tax = tax;
            }

            public static class BasePriceEntity {
                private String display;
                private String currency;
                private int value;
                private String major_value;

                public String getDisplay() {
                    return display;
                }

                public void setDisplay(String display) {
                    this.display = display;
                }

                public String getCurrency() {
                    return currency;
                }

                public void setCurrency(String currency) {
                    this.currency = currency;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }

                public String getMajor_value() {
                    return major_value;
                }

                public void setMajor_value(String major_value) {
                    this.major_value = major_value;
                }
            }

            public static class EventbriteFeeEntity {
                private String display;
                private String currency;
                private int value;
                private String major_value;

                public String getDisplay() {
                    return display;
                }

                public void setDisplay(String display) {
                    this.display = display;
                }

                public String getCurrency() {
                    return currency;
                }

                public void setCurrency(String currency) {
                    this.currency = currency;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }

                public String getMajor_value() {
                    return major_value;
                }

                public void setMajor_value(String major_value) {
                    this.major_value = major_value;
                }
            }

            public static class GrossEntity {
                private String display;
                private String currency;
                private int value;
                private String major_value;

                public String getDisplay() {
                    return display;
                }

                public void setDisplay(String display) {
                    this.display = display;
                }

                public String getCurrency() {
                    return currency;
                }

                public void setCurrency(String currency) {
                    this.currency = currency;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }

                public String getMajor_value() {
                    return major_value;
                }

                public void setMajor_value(String major_value) {
                    this.major_value = major_value;
                }
            }

            public static class PaymentFeeEntity {
                private String display;
                private String currency;
                private int value;
                private String major_value;

                public String getDisplay() {
                    return display;
                }

                public void setDisplay(String display) {
                    this.display = display;
                }

                public String getCurrency() {
                    return currency;
                }

                public void setCurrency(String currency) {
                    this.currency = currency;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }

                public String getMajor_value() {
                    return major_value;
                }

                public void setMajor_value(String major_value) {
                    this.major_value = major_value;
                }
            }

            public static class TaxEntity {
                private String display;
                private String currency;
                private int value;
                private String major_value;

                public String getDisplay() {
                    return display;
                }

                public void setDisplay(String display) {
                    this.display = display;
                }

                public String getCurrency() {
                    return currency;
                }

                public void setCurrency(String currency) {
                    this.currency = currency;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }

                public String getMajor_value() {
                    return major_value;
                }

                public void setMajor_value(String major_value) {
                    this.major_value = major_value;
                }
            }
        }
    }
}
