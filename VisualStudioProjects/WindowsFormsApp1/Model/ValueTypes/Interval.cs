using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model.ValueTypes
{
    public class Interval
    {
        public DateTime StartDate { get; }
        public DateTime EndDate { get; }
        public string Comment { get; }
        //public long StartX { get; set; }
        //public long EndX { get; set; }

        public Interval(DateTime startDateTime, DateTime? endDateTime, string comment)
        {
            if (endDateTime.HasValue && startDateTime > endDateTime.Value)
            {
                throw new ArgumentException("StartDateTime > EndDateTime is not allowed.");
            }
        }
    }
}