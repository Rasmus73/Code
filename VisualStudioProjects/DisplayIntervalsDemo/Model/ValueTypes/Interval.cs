using System;

namespace Model.ValueTypes
{
    public class IntervalType
    {
        public DateTime StartDateTime { get; }
        public DateTime? EndDateTime { get; }
        public string Comment { get; }
        // 
        public long XStart { get; set; }
        public long XEnd { get; set; }
        public long Y { get; set; }

        public IntervalType(DateTime startDateTime, DateTime? endDateTime, string comment)
        {
            if (endDateTime.HasValue && startDateTime > endDateTime.Value)
            {
                throw new ArgumentException("StartDateTime > EndDateTime is not allowed.");
            }

            StartDateTime = startDateTime;
            EndDateTime = endDateTime;
            Comment = comment;
        }
    }
}