using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Model.ValueTypes;

namespace CompositeClassLibrary.CompositePattern
{
    public class ComponentContactGroup : IntervalComponent, IComponent
    {
        public string Name { get; set; }

        public ComponentContactGroup(string name, IntervalType interval)
            : base(interval)
        {            
            Name = name;
        }

        public void Add(IComponent component)
        {
            throw new NotImplementedException();
        }

        public void Remove(IComponent component)
        {
            throw new NotImplementedException();
        }    

        public void Draw(Graphics graphics)
        {

            long y = base.Interval.Y;
            Pen myPen;
            myPen = new Pen(Color.Black);
            graphics.DrawLine(myPen, base.Interval.XStart, y, base.Interval.XEnd, y);

            graphics.DrawLine(myPen, base.Interval.XStart, y-5, base.Interval.XStart, y+5);
            graphics.DrawLine(myPen, base.Interval.XEnd, y-5, base.Interval.XEnd, y+5);


            Font font = new Font("TimesNewRoman", 8);
            //graphics.DrawString(Name, font, new SolidBrush(Color.Black), base.Interval.XEnd - base.Interval.XStart, y);
            graphics.DrawString(Name, font, new SolidBrush(Color.Black), base.Interval.XStart, y);

            graphics.DrawString(base.Interval.StartDateTime.ToString(), font, new SolidBrush(Color.Black), base.Interval.XStart, y-20);
            graphics.DrawString(base.Interval.EndDateTime.ToString(), font, new SolidBrush(Color.Black), base.Interval.XEnd, y-20);

        }
    }
}