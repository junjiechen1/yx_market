using SQLite;
using System;
using System.Collections.Generic;
using System.Text;

namespace yx_marketplace.Model
{
    public class CompanyInfo
    {
        [PrimaryKey,AutoIncrement] // auto increment 1,2,3,4,5.....
        public int Id { get; set; }

        [MaxLength(500)]//set max length of the list to 500 
        //return error if >500
        public string AppName { get; set; }
        public string CompanyName { get; set; }
        public string Categorystate { get; set; }
    }
}
