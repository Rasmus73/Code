//using System;
//using System.Collections.Generic;
//using System.Linq;
//using System.Text;
//using System.Threading.Tasks;

//namespace Model
//{
//    public class MyPage
//    {
//        private int Width = 500;
//        private int Height = 1000;

//        List<Interval> intervals = new List<Interval>();

//        public MyPage(int width)
//        {
//            Width = width;
//        }

//        //public long resolutionUnit(DateTime min, DateTime max)
//        //{
//        //    var unit = (max.Ticks - min.Ticks) / Width;
//        //    return unit;
//        //}
//        private long resolutionUnit()
//        {
//            var minTicks = intervals.Min(s => s.StartDate).Ticks;
//            var maxTicks = intervals.Max(s => s.EndDate).Ticks;
//            var ticksPerPixel = (maxTicks - minTicks) / Width;
//            return ticksPerPixel;
//        }


//        public int resolutionUnit(int ticks)
//        {
//            return ticks / Width;
//        }

//        public void AddInterval(DateTime start, DateTime end, string comment)
//        {
//            intervals.Add(new Interval
//            {
//                StartDate = start,
//                EndDate = end,
//                Comment = comment
//            });
//        }

//        public List<Interval> GetIntervals()
//        {
//            var ticksPerPixel = resolutionUnit();

//            var minTicks = intervals.Min(s => s.StartDate).Ticks;

//            foreach (var interval in intervals)
//            {
//                interval.StartX = (interval.StartDate.Ticks - minTicks) / ticksPerPixel;
//                interval.EndX = (interval.EndDate.Ticks - minTicks) / ticksPerPixel;
//            }

//            return intervals;
//        }
//    }
//}