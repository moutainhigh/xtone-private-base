package com.wns.push.bean;

public class push_policy_ext extends push_policy
{

    /**
     * 
     */
    private static final long serialVersionUID = 769407020141070405L;
    String                    ditstict;
    Integer                   admin_status;

    public push_policy_ext()
    { 
        
    }
    
    public push_policy_ext(push_policy item)
    {
        setId(item.getId());
        setType(item.getType());
        setSubtype(item.getSubtype());
        setRes_id(item.getRes_id());
        setContent(item.getContent());
        setUpdatedate(item.getUpdatedate());
        setBegintime(item.getBegintime());
        setEndtime(item.getEndtime());
        setName(item.getName());
        setStatus(item.getStatus());
        setTitle(item.getTitle());
        setLink(item.getLink());
        setAll_push(item.getAll_push());
        setChannel(item.getChannel());
        setOpen(item.getOpen());
        setCloseh_nam(item.getCloseh_nam());
        setCesu(item.getCesu());
        setState(item.getState());
        setSize(item.getSize());
        setArea(item.getArea());
        setImg_link(item.getImg_link());
        setDowncount(item.getDowncount());
        setClose(item.getClose());
        setName(item.getName());
        setRes_loc(item.getRes_loc());
        setRom(item.getRom());
        setDown_direct(item.getDown_direct());
        setChannelname(item.getChannelname());
        setCanal_switch(item.getCanal_switch());
        setPush_count(item.getPush_count());
        setWeight(item.getWeight());
        setNetwork(item.getNetwork());
        setIsRetry(item.getIsRetry());
    }
    
    public String getDitstict()
    {
        return ditstict;
    }

    public void setDitstict(String ditstict)
    {
        this.ditstict = ditstict;
    }

    public Integer getAdmin_status()
    {
        return admin_status;
    }

    public void setAdmin_status(Integer adminStatus)
    {
        admin_status = adminStatus;
    }

}
