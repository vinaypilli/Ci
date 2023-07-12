package com.teamcomputers.dto;

import java.util.List;

public class LatestMessageListShowResponseDTO {
    private int openCount;
    
//    private int totalCount;
    private List<LatestMessageDTO> open;
    private int closedCount;
    private List<LatestMessageDTO> closed;

    // Getters and Setters

    public int getOpenCount() {
        return openCount;
    }

    public void setOpenCount(int openCount) {
        this.openCount = openCount;
    }

    public int getClosedCount() {
        return closedCount;
    }

    public void setClosedCount(int closedCount) {
        this.closedCount = closedCount;
    }

//    public int getTotalCount() {
//        return totalCount;
//    }
//
//    public void setTotalCount(int totalCount) {
//        this.totalCount = totalCount;
//    }

    public List<LatestMessageDTO> getOpen() {
        return open;
    }

    public void setOpen(List<LatestMessageDTO> open) {
        this.open = open;
    }

    public List<LatestMessageDTO> getClosed() {
        return closed;
    }

    public void setClosed(List<LatestMessageDTO> closed) {
        this.closed = closed;
    }

    public static class LatestMessageDTO {
        private Long id;
        private String phoneNo;
        private String fullName;
        private String time;
        private String lastMsg;
        private String messagetype;
        private long assignedto;
        private int count;

        // Getters and Setters

        
        
        public Long getId() {
            return id;
        }

        public long getAssignedto() {
			return assignedto;
		}

		public void setAssignedto(long assignedto) {
			this.assignedto = assignedto;
		}

		public String getMessagetype() {
			return messagetype;
		}

		public void setMessagetype(String messagetype) {
			this.messagetype = messagetype;
		}

		public void setId(Long id) {
            this.id = id;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLastMsg() {
            return lastMsg;
        }

        public void setLastMsg(String lastMsg) {
            this.lastMsg = lastMsg;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
